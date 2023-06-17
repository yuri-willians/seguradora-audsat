package com.audsat.seguradora.core.insurance.controller;

import com.audsat.seguradora.core.commons.dto.ResponseBase;
import com.audsat.seguradora.core.insurance.dto.*;
import com.audsat.seguradora.core.insurance.service.CreateInsurance;
import com.audsat.seguradora.core.insurance.service.DeleteInsurance;
import com.audsat.seguradora.core.insurance.service.GetInsuranceDetailById;
import com.audsat.seguradora.core.insurance.service.UpdateInsurance;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/insurance")
public class InsuranceController {

    private final CreateInsurance createInsurance;

    private final UpdateInsurance updateInsurance;

    private final GetInsuranceDetailById getInsuranceDetailById;

    private final DeleteInsurance deleteInsurance;

    @Autowired
    public InsuranceController(
            final CreateInsurance createInsurance,
            final UpdateInsurance updateInsurance,
            final GetInsuranceDetailById getInsuranceDetailById,
            final DeleteInsurance deleteInsurance
    ) {
        this.createInsurance = createInsurance;
        this.updateInsurance = updateInsurance;
        this.getInsuranceDetailById = getInsuranceDetailById;
        this.deleteInsurance = deleteInsurance;
    }

    @PostMapping("/budget")
    public ResponseEntity<ResponseBase<CreateInsuranceResponse>> create(
            @Valid @RequestBody final CreateInsuranceRequest request
    ) {
        final var response = this.createInsurance.execute(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ResponseBase.success(response));
    }

    @PutMapping("/budget/{insuranceId}")
    public ResponseEntity<ResponseBase<UpdateInsuranceResponse>> update(
            @PathVariable("insuranceId") final Long idInsurance,
            @RequestBody final UpdateInsuranceRequest request
    ) {
        final var response = this.updateInsurance.execute(idInsurance, request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ResponseBase.success(response));
    }

    @GetMapping("/budget/{insuranceId}")
    public ResponseEntity<ResponseBase<GetInsuranceByIdResponse>> getById(
            @PathVariable("insuranceId") final Long idInsurance
    ) {
        final var response = this.getInsuranceDetailById.execute(idInsurance);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseBase.success(response));
    }

    @DeleteMapping("/budget/{insuranceId}")
    public ResponseEntity<ResponseBase<Void>> delete(
            @PathVariable("insuranceId") final Long idInsurance
    ) {
        this.deleteInsurance.execute(idInsurance);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(ResponseBase.success());
    }

}
