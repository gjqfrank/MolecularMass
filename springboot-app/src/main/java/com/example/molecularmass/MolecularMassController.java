package com.example.molecularmass;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MolecularMassController {

    private final MolecularMassService service;

    public MolecularMassController(MolecularMassService service) {
        this.service = service;
    }

    @GetMapping("/calculate")
    public CalculationResult calculate(@RequestParam String formula) {
        double mass = service.calculateMass(formula);
        if (mass < 0) {
            return new CalculationResult(false, "无效的化学式，请检查输入", 0);
        }
        return new CalculationResult(true, "计算成功", mass);
    }
}