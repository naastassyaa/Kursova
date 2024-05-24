package api.controller;

import api.DTO.BmpDataDTO;
import api.exceptions.ItemNotFoundException;
import api.models.BmpData;
import api.service.BmpDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

    @CrossOrigin(origins = "*")
    @RestController
    @RequestMapping(path = "/bmp_data")
    public class BmpDataController {
        private static final Logger LOGGER = LoggerFactory.getLogger(BmpDataController.class);

        @Autowired
        private BmpDataService bmpDataService;

        @PostMapping
        public ResponseEntity<BmpDataDTO> createBmpData(@Valid @RequestBody final BmpData bmpData) {
            LOGGER.info("Added new data values");
            return new ResponseEntity<BmpDataDTO>(new BmpDataDTO(bmpDataService.addData(bmpData)), HttpStatus.OK);
        }
        @GetMapping
        public ResponseEntity<List<BmpDataDTO>> getAllData() {
            LOGGER.info("Gave away all Bmp Sensor Data ");
            List<BmpData> bmpDatas = bmpDataService.getData();
            List<BmpDataDTO> bmpDataDTOS = new ArrayList<>();
            for (BmpData bmpData:bmpDatas) {
                BmpDataDTO bmpDataDTO = new BmpDataDTO(bmpData);
                bmpDataDTOS.add(bmpDataDTO);
            }
            return new ResponseEntity<List<BmpDataDTO>>(bmpDataDTOS, HttpStatus.OK);
        }

        @GetMapping(path = "/{id}")
        public ResponseEntity<BmpDataDTO> getData(@PathVariable(name = "id")final Integer id){
            if (bmpDataService.getDataById(id) == null) {
                LOGGER.error("Can't get(getData) an Bmp_Data with non-existing id: " + id);
                throw new ItemNotFoundException("Can't get(getData) an Bmp_Data with non-existing id: " + id);
            }
            LOGGER.info("Successfully get an Bmp_Data with id: " + id);
            BmpData bmpData = bmpDataService.getDataById(id);
            return new ResponseEntity<BmpDataDTO>(new BmpDataDTO(bmpData), HttpStatus.OK);
        }

        @DeleteMapping(path = "/{id}")
        public ResponseEntity<BmpData> deleteDataById(@PathVariable("id") final Integer id) {
            if (bmpDataService.getDataById(id) == null) {
                LOGGER.error("Can't delete(deleteDataById) an Bmp_Data with non-existing id: " + id);
                throw new ItemNotFoundException("Can't delete(deleteDataById) an Bmp_Data with non-existing id: " + id);
            }
            LOGGER.info("Successfully deleted Bmp_Data with id: " + id);
            bmpDataService.deleteDataById(id);
            return ResponseEntity.noContent().build();
        }
    }

