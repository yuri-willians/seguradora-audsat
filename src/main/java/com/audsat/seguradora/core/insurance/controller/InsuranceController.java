package com.audsat.seguradora.core.insurance.controller;

import com.audsat.seguradora.core.commons.dto.ResponseBase;
import com.audsat.seguradora.core.insurance.dto.CreateInsuranceRequest;
import com.audsat.seguradora.core.insurance.dto.CreateInsuranceResponse;
import com.audsat.seguradora.core.insurance.service.CreateInsurance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/insurance")
public class InsuranceController {

    private final CreateInsurance createInsurance;

    @Autowired
    public InsuranceController(final CreateInsurance createInsurance) {
        this.createInsurance = createInsurance;
    }

    @PostMapping("/budget")
    public ResponseEntity<ResponseBase<CreateInsuranceResponse>> create(
            @RequestBody final CreateInsuranceRequest request
    ) {
        final var response = this.createInsurance.execute(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ResponseBase.success(response));
    }

}
