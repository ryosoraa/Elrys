package com.ryo.elrys.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.ryo.elrys.model.AccountsModel;
import com.ryo.elrys.model.DataModel;
import com.ryo.elrys.payload.BodyResponse;
import com.ryo.elrys.payload.DataResponse;
import com.ryo.elrys.service.interfaces.UserService;
import io.swagger.annotations.Api;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
@Api(value = "User", tags = "User-Controller")
public class UserController {

        @Autowired
        UserService userService;

        /**
         * Register akun baru
         * 
         * @param accountsModel objek model akun
         * @return respon status dan data akun baru
         */
        @PostMapping("/register")
        public ResponseEntity<BodyResponse<DataResponse>> register(@RequestBody AccountsModel accountsModel)
                        throws Exception {
                return ResponseEntity.ok()
                                .body(userService.register(accountsModel));
        }

        /**
         * proses login user.
         *
         * @param accountsModel Objek model akun yang akan digunakan untuk login.
         * @param requestHeader untuk mendapatkan header dari request.
         * @return ResponseEntity berisi BodyResponse dengan status dan data login.
         * @throws Exception Jika terjadi kesalahan selama proses login.
         */
        @PostMapping("/login")
        public ResponseEntity<BodyResponse<DataResponse>> login(@RequestBody AccountsModel accountsModel,
                        HttpServletRequest requestHeader) throws Exception {
                return ResponseEntity.ok()
                                .body(userService.login(accountsModel, requestHeader));

        }

        /**
         * Mengambil informasi user berdasarkan alamat email.
         *
         * @param email Alamat email user.
         * @return ResponseEntity berisi BodyResponse dengan status dan informasi user.
         * @throws Exception Jika terjadi kesalahan saat mengambil informasi user.
         */
        @GetMapping("/getInfo")
        public ResponseEntity<BodyResponse<JsonNode>> getInfo(@RequestParam String email) throws Exception {
                return ResponseEntity.ok()
                                .body(userService.getInfo(email));

        }

        /**
         * Memperbarui informasi user.
         *
         * @param dataModel Objek model data yang akan digunakan untuk memperbarui informasi user.
         * @return ResponseEntity berisi BodyResponse dengan status dan informasi user yang diperbarui.
         * @throws Exception Jika terjadi kesalahan selama proses pembaruan.
         */
        @PutMapping("/update")
        public ResponseEntity<BodyResponse<JsonNode>> update(@RequestBody DataModel dataModel) throws Exception {
                return ResponseEntity.ok()
                                .body(userService.update(dataModel));

        }

        /**
         * Menghapus akun user.
         *
         * @param accountsModel Objek model akun yang akan dihapus.
         * @return ResponseEntity berisi BodyResponse dengan status hasil penghapusan.
         * @throws Exception Jika terjadi kesalahan selama proses penghapusan.
         */
        @DeleteMapping("/delete")
        public ResponseEntity<BodyResponse<JsonNode>> delete(@RequestBody AccountsModel accountsModel)
                        throws Exception {
                return ResponseEntity.ok()
                                .body(userService.delete(accountsModel));

        }

        /**
         * Mengganti kata sandi user.
         *
         * @param email   email user.
         * @param oldPass Kata sandi lama user.
         * @param newPass Kata sandi baru yang akan diatur.
         * @return ResponseEntity berisi BodyResponse dengan status hasil perubahan kata sandi.
         * @throws Exception Jika terjadi kesalahan selama proses perubahan kata sandi.
         */
        @GetMapping("/pass")
        public ResponseEntity<BodyResponse<JsonNode>> changePass(
                        @RequestParam String email,
                        @RequestParam String oldPass,
                        @RequestParam String newPass) throws Exception {
                return ResponseEntity.ok()
                                .body(userService.changePassword(email, oldPass, newPass));

        }

}
