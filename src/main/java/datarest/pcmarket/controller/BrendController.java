package datarest.pcmarket.controller;

import datarest.pcmarket.entity.Blog;
import datarest.pcmarket.entity.Brend;
import datarest.pcmarket.payload.BlogDto;
import datarest.pcmarket.payload.BrendDto;
import datarest.pcmarket.payload.Result;
import datarest.pcmarket.service.BlogService;
import datarest.pcmarket.service.BrendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brend")
public class BrendController {
    @Autowired
    BrendService brendService;

    @PreAuthorize(value = "hasRole('SUPER_ADMIN')")
//    @PreAuthorize(value = "hasAuthority('CONTROL_BREND')")
    @GetMapping
    public HttpEntity<?> getBrends(){
        List<Brend> brends = brendService.getBrendsService();
        return ResponseEntity.status(brends.isEmpty()?409:200).body(brends);
    }
//    @PreAuthorize(value = "hasRole('SUPER_ADMIN')")
    @PreAuthorize(value = "hasRole('CONTROL_BREND')")
    @GetMapping("/{id}")
    public HttpEntity<?> getBrend(@PathVariable Integer id){
        Brend brend = brendService.getBrendService(id);
        return ResponseEntity.status(brend!=null?200:409).body(brend);
    }
    @PreAuthorize(value = "hasRole('SUPER_ADMIN')")
    @PostMapping("/add")
    public HttpEntity<?> addBrend(@RequestBody BrendDto brendDto){
        Result result = brendService.addBrendService(brendDto);
        return ResponseEntity.status(result.isSuccess()?200:409).body(result);
    }
    @PreAuthorize(value = "hasRole('SUPER_ADMIN')")
    @PutMapping("/edit/{id}")
    public HttpEntity<?> editBrend(@RequestBody BrendDto brendDto,@PathVariable Integer id){
        Result result = brendService.editBrendService(brendDto,id);
        return ResponseEntity.status(result.isSuccess()?200:409).body(result);
    }
    @PreAuthorize(value = "hasRole('SUPER_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> deleteBrend(@PathVariable Integer id){
        Result result = brendService.deleteBrendService(id);
        return ResponseEntity.status(result.isSuccess()?201:409).body(result);
    }
}
