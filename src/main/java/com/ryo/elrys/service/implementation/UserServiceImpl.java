package com.ryo.elrys.service.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ryo.elrys.enums.BodyUrl;
import com.ryo.elrys.exceptions.UserAlreadyExistsException;
import com.ryo.elrys.exceptions.UserNotFoundException;
import com.ryo.elrys.model.AccountsModel;
import com.ryo.elrys.model.DataModel;
import com.ryo.elrys.model.LoginModel;
import com.ryo.elrys.payload.BodyResponse;
import com.ryo.elrys.payload.DataResponse;
import com.ryo.elrys.service.interfaces.UserService;
import com.ryo.elrys.utils.Equipment;
import com.ryo.elrys.utils.Query;
import com.ryo.elrys.utils.api.RequestApi;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserServiceImpl implements UserService {

    @Autowired
    Equipment equipment;

    @Autowired
    RequestApi requestApi;

    @Autowired
    ObjectMapper mapper;

    /**
     * Mendaftarkan user baru
     *
     * @param accountsModel Objek model akun yang akan didaftarkan.
     * @return ResponseEntity berisi BodyResponse dengan status dan data registrasi.
     * @throws Exception Jika terjadi kesalahan selama proses registrasi.
     */
    @Override
    public BodyResponse<DataResponse> register(AccountsModel accountsModel) throws Exception {
        JsonNode register = requestApi.register(
                BodyUrl.MAIN_CREATED.getUrl().concat(equipment.idEncoder(accountsModel.getEmail())),
                mapper.writeValueAsString(new DataModel(accountsModel)));

        if (register.has("error")) {
            throw new UserAlreadyExistsException("User Already Exists");
        }

        return BodyResponse.<DataResponse>builder()
                .status("Success")
                .data(new DataResponse(accountsModel))
                .message("Resister Success")
                .build();
    }

    /**
     * login user
     *
     * @param accountsModel Objek model akun yang digunakan untuk login.
     * @param requestHeader HttpServletRequest untuk mendapatkan header dari request.
     * @return ResponseEntity berisi BodyResponse dengan status dan data login.
     * @throws Exception Jika terjadi kesalahan selama proses login.
     */
    @Override
    public BodyResponse<DataResponse> login(AccountsModel accountsModel, HttpServletRequest requestHeader)
            throws Exception {

        JsonNode responds = requestApi.findByRequest(BodyUrl.MAIN_SEARCH.getUrl(),
                Query.SearchByEmailAndPass(accountsModel.getEmail(), accountsModel.getPassword()));

        if (!String.valueOf(responds.at("/hits/total/value")).equals("1")) {
            throw new UserNotFoundException("User Not Found");
        }

        requestApi.login(BodyUrl.LOG_DOC.getUrl(),
                mapper.writeValueAsString(new LoginModel(accountsModel, requestHeader)));

        return BodyResponse.<DataResponse>builder()
                .status("Success")
                .data(new DataResponse(accountsModel))
                .message("Login Success")
                .build();

    }

    /**
     * Mengambil informasi user berdasarkan alamat email.
     *
     * @param email Alamat email user.
     * @return ResponseEntity berisi BodyResponse dengan status dan informasi user.
     * @throws Exception Jika terjadi kesalahan selama proses pengambilan informasi.
     */
    @Override
    public BodyResponse<JsonNode> getInfo(String email) throws Exception {

        JsonNode response = requestApi.getInfo(BodyUrl.MAIN_SEARCH.getUrl(), Query.SearchByEmail(email));
        if (!String.valueOf(response.at("/hits/hits/0/_source/email").asText()).equals(email)) {
            throw new UserNotFoundException("User Not Found");
        }

        return BodyResponse.<JsonNode>builder()
                .status("Success")
                .data(response)
                .message("Get Info Success")
                .build();
    }

    /**
     * Memperbarui informasi user.
     *
     * @param dataModel Objek model data yang akan digunakan untuk memperbarui informasi user.
     * @return ResponseEntity berisi BodyResponse dengan status dan informasi user yang diperbarui.
     * @throws Exception Jika terjadi kesalahan selama proses pembaruan.
     */
    @Override
    public BodyResponse<JsonNode> update(DataModel dataModel) throws Exception {

        JsonNode response = requestApi.findByRequest(BodyUrl.MAIN_SEARCH.getUrl(),
                Query.SearchByEmailAndPass(dataModel.getEmail(), dataModel.getPassword()));
        if (!String.valueOf(response.at("/hits/total/value")).equals("1")) {
            throw new UserNotFoundException("User Not Found");
        }
        return BodyResponse.<JsonNode>builder()
                .status("Success")
                .data(requestApi.update(BodyUrl.MAIN_DOC.getUrl().concat(response.at("/hits/hits/0/_id").asText()),
                        mapper.writeValueAsString(dataModel)))
                .message("Update Success")
                .build();
    }

    /**
     * Menghapus akun user.
     *
     * @param accountsModel Objek model akun yang akan dihapus.
     * @return ResponseEntity berisi BodyResponse dengan status hasil penghapusan.
     * @throws Exception Jika terjadi kesalahan selama proses penghapusan.
     */
    @Override
    public BodyResponse<JsonNode> delete(AccountsModel accountsModel) throws Exception {

        JsonNode response = requestApi.delete(BodyUrl.MAIN_DELETE_QUERY.getUrl(),
                Query.SearchByEmailAndPass(accountsModel.getEmail(), accountsModel.getPassword()));
        System.out.println(response.toPrettyString());

        if (String.valueOf(response.at("/deleted")).equals("0")) {
            throw new UserNotFoundException("User Not Found");
        }
        return BodyResponse.<JsonNode>builder()
                .status("Success")
                .data(requestApi.delete(BodyUrl.LOG_DELETE_QUERY.getUrl(),
                        Query.SearchByEmail(accountsModel.getEmail())))
                .message("Deleted Success")
                .build();
    }

    /**
     * Mengganti kata sandi user.
     *
     * @param email       Alamat email user.
     * @param oldPassword Kata sandi lama user.
     * @param newPassword Kata sandi baru yang akan diatur.
     * @return ResponseEntity berisi BodyResponse dengan status hasil perubahan kata sandi.
     * @throws Exception Jika terjadi kesalahan selama proses perubahan kata sandi.
     */
    @Override
    public BodyResponse<JsonNode> changePassword(String email, String oldPassword, String newPassword)
            throws Exception {

        JsonNode response = requestApi.changePassword(BodyUrl.MAIN_UPDATE_QUERY.getUrl(),
                Query.UpdatePass(email, oldPassword, newPassword));
        if (!String.valueOf(response.at("/total")).equals("1")) {
            throw new UserNotFoundException("User Not Found");
        }
        return BodyResponse.<JsonNode>builder()
                .status("Success")
                .data(response)
                .message("Get Info Success")
                .build();
    }

}
