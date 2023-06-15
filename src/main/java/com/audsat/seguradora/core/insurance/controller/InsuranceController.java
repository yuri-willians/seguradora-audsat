package com.audsat.seguradora.core.insurance.controller;

import com.audsat.seguradora.core.commons.dto.ResponseBase;
import com.audsat.seguradora.core.insurance.dto.CreateInsuranceRequest;
import com.audsat.seguradora.core.insurance.dto.CreateInsuranceResponse;
import com.audsat.seguradora.core.insurance.dto.GetInsuranceByIdResponse;
import com.audsat.seguradora.core.insurance.service.CreateInsurance;
import com.audsat.seguradora.core.insurance.service.GetInsuranceDetailById;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/insurance")
public class InsuranceController {

    private final CreateInsurance createInsurance;

    private final GetInsuranceDetailById getInsuranceDetailById;

    @Autowired
    public InsuranceController(
            final CreateInsurance createInsurance,
            final GetInsuranceDetailById getInsuranceDetailById
    ) {
        this.createInsurance = createInsurance;
        this.getInsuranceDetailById = getInsuranceDetailById;
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

    @GetMapping("/budget/{insuranceId}")
    public ResponseEntity<ResponseBase<GetInsuranceByIdResponse>> getById(
            @PathVariable("insuranceId") final Long idInsurance
    ) {
        final var response = this.getInsuranceDetailById.execute(idInsurance);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseBase.success(response));
    }

}
