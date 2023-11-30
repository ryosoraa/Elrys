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

        @PostMapping("/addFilm")
        public ResponseEntity<BodyResponse<JsonNode>> addFilm(@RequestBody FilmtModel filmtModel) throws Exception {
                return ResponseEntity.ok()
                                .body(filmService.addProduct(filmtModel));
        }

        @GetMapping("/search")
        public ResponseEntity<BodyResponse<JsonNode>> search(
                        @Parameter(explode = Explode.TRUE, name = "type", in = ParameterIn.QUERY, description = "search film by conditions", schema = @Schema(type = "String", defaultValue = "title", allowableValues = {
                                        "type", "title", "director", "release_year", "genre", "rating", "format",
                                        "runtime", "language", "subtitles",
                                        "price" })) @RequestParam(name = "type", defaultValue = "title") String type,
                        @Parameter(explode = Explode.TRUE, name = "value", in = ParameterIn.QUERY, description = "value to search", schema = @Schema(type = "String", defaultValue = "Harry Potter")) @RequestParam(name = "value", defaultValue = "Harry potter") String value

        ) throws Exception {
                return ResponseEntity.ok()
                                .body(filmService.search(type, value));
        }

        @GetMapping("/analyst")
        public ResponseEntity<BodyResponse<JsonNode>> analyst(
                        @Parameter(explode = Explode.TRUE, name = "type", in = ParameterIn.QUERY, description = "search film by conditions", schema = @Schema(type = "String", defaultValue = "genre", allowableValues = {
                                        "type", "director", "release_year", "genre", "rating", "format", "runtime",
                                        "language", "subtitles",
                                        "price" })) @RequestParam(name = "type", defaultValue = "genre") String type)
                        throws Exception {
                return ResponseEntity.ok()
                                .body(filmService.analyst(type));
        }

}
