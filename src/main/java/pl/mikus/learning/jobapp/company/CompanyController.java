package pl.mikus.learning.jobapp.company;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mikus.learning.jobapp.common.LogTimeExecution;

@Slf4j
@RestController
@RequestMapping("/companies")
@LogTimeExecution
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(companyService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> createCompany(@RequestBody Company company) {
        return ResponseEntity.ok(companyService.createCompany(company));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findCompanyById(@PathVariable Long id) {
        return ResponseEntity.of(companyService.findCompany(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCompany(@PathVariable Long id, @RequestBody Company company) {
        return ResponseEntity.of(companyService.updateCompany(id, company));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCompanyById(@PathVariable Long id) {
        if (companyService.deleteCompany(id)) return ResponseEntity.ok().build();
        return ResponseEntity.notFound().build();
    }
}
