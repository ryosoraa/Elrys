package com.ryo.elrys.service.implementation;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ryo.elrys.enums.BodyUrl;
import com.ryo.elrys.exceptions.UserNotFoundException;
import com.ryo.elrys.model.FilmtModel;
import com.ryo.elrys.payload.BodyResponse;
import com.ryo.elrys.service.interfaces.FilmService;
import com.ryo.elrys.utils.Equipment;
import com.ryo.elrys.utils.Query;
import com.ryo.elrys.utils.api.RequestApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilmServiceImpl implements FilmService {

    @Autowired
    RequestApi requestApi;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    Equipment equipment;

    /**
     * Menambahkan film
     *
     * @param filmtModel Objek model film yang akan ditambahkan.
     * @return ResponseEntity berisi BodyResponse dengan status dan data penambahan produk film.
     * @throws Exception Jika terjadi kesalahan selama proses penambahan film.
     */
    @Override
    public BodyResponse<JsonNode> addProduct(FilmtModel filmtModel) throws Exception {
        if (!String.valueOf(
                requestApi.findByRequest(BodyUrl.MAIN_SEARCH.getUrl(), Query.SearchByEmail(filmtModel.getEmail()))
                        .at("/hits/total/value"))
                .equals("1")) {
            throw new UserNotFoundException("User Not Found");
        }

        return BodyResponse.<JsonNode>builder()
                .status("Success")
                .data(requestApi.addFilm(BodyUrl.FILM_DOC.getUrl(), mapper.writeValueAsString(filmtModel)))
                .message("Add Product Success")
                .build();
    }

    /**
     * Mencari film berdasarkan jenis pencarian dan nilai pencarian.
     *
     * @param type  Jenis pencarian (contoh: "title", "director", "genre").
     * @param value Nilai pencarian yang akan digunakan.
     * @return ResponseEntity berisi BodyResponse dengan status dan data hasil pencarian.
     * @throws Exception Jika terjadi kesalahan selama proses pencarian.
     */
    @Override
    public BodyResponse<JsonNode> search(String type, String value) throws Exception {
        JsonNode response = requestApi.searchFilm(BodyUrl.FILM_SEARCH.getUrl(), Query.search(type, value));

        if (String.valueOf(response.at("/hits/total/value")).equals("0")) {
            throw new UserNotFoundException("Request Not Found");
        }

        return BodyResponse.<JsonNode>builder()
                .status("Success")
                .data(response.path("hits").path("hits"))
                .message("Data Founded")
                .build();
    }

    /**
     * Menganalisis data film berdasarkan jenis analisis yang diberikan.
     *
     * @param type Jenis analisis (contoh: "type", "director", "genre", "format", "language", "subtitles")
     * . Juga dapat berupa ("release", "rating", "price").
     * @return ResponseEntity berisi BodyResponse dengan status dan hasil analisis data film.
     * @throws Exception Jika terjadi kesalahan selama proses analisis.
     */
    @Override
    public BodyResponse<JsonNode> analyst(String type) throws Exception {
        JsonNode response = switch (type) {
            case "type", "director", "genre", "format", "language", "subtitles" ->
                requestApi.analyst(BodyUrl.FILM_SEARCH.getUrl(), Query.categorizText(type));

            case "release", "rating", "price" ->
                requestApi.analyst(BodyUrl.FILM_SEARCH.getUrl(), Query.filmYearAnalyst(type));

            default -> throw new IllegalArgumentException("Unexpected value: " + type);
        };

        if (String.valueOf(response.at("/hits/total/value")).equals("0")) {
            throw new UserNotFoundException("Request Not Found");
        }

        return BodyResponse.<JsonNode>builder()
                .status("Success")
                .data(response)
                .message("Data Founded")
                .build();
    }
}
