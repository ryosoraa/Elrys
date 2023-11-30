package com.ryo.elrys.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.ryo.elrys.model.FilmtModel;
import com.ryo.elrys.payload.BodyResponse;
import com.ryo.elrys.service.interfaces.FilmService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.Explode;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class FilmController {

        @Autowired
        FilmService filmService;

        /**
         * Menambahkan film baru
         * 
         * @param filmtModel objek model film
         * @return respon berupa status dan data film baru
         */
        @PostMapping("/addFilm")
        public ResponseEntity<BodyResponse<JsonNode>> addFilm(@RequestBody FilmtModel filmtModel) throws Exception {
                return ResponseEntity.ok()
                                .body(filmService.addProduct(filmtModel));
        }

        /**
         * Mencari film berdasarkan kriteria
         * 
         * @param type  jenis pencarian (judul, sutradara, dll)
         * @param value nilai kriteria pencarian
         * @return respon berupa status dan data film hasil pencarian
         */
        @GetMapping("/search")
        public ResponseEntity<BodyResponse<JsonNode>> search(
                        @Parameter(explode = Explode.TRUE, name = "type", in = ParameterIn.QUERY, description = "search film by conditions", schema = @Schema(type = "String", defaultValue = "title", allowableValues = {
                                        "type", "title", "director", "release", "genre", "rating", "format",
                                        "runtime", "language", "subtitles",
                                        "price" })) @RequestParam(name = "type", defaultValue = "title") String type,
                        @Parameter(explode = Explode.TRUE, name = "value", in = ParameterIn.QUERY, description = "value to search", schema = @Schema(type = "String", defaultValue = "Harry Potter")) @RequestParam(name = "value", defaultValue = "Harry potter") String value

        ) throws Exception {
                return ResponseEntity.ok()
                                .body(filmService.search(type, value));
        }

        /**
         * Analyst film berdasarkan kriteria
         * 
         * @param type jenis pencarian (judul, sutradara, dll)
         * @return respon berupa status dan jumlah data film hasil pencarian
         */
        @GetMapping("/analyst")
        public ResponseEntity<BodyResponse<JsonNode>> analyst(
                        @Parameter(explode = Explode.TRUE, name = "type", in = ParameterIn.QUERY, description = "search film by conditions", schema = @Schema(type = "String", defaultValue = "genre", allowableValues = {
                                        "type", "director", "release", "genre", "rating", "format",
                                        "language", "subtitles",
                                        "price" })) @RequestParam(name = "type", defaultValue = "genre") String type)
                        throws Exception {
                return ResponseEntity.ok()
                                .body(filmService.analyst(type));
        }

}
