package springmvcdemo1.controller;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import springmvcdemo1.databinding.Price;

import java.beans.PropertyEditorSupport;

@RestController
@RequestMapping("/data-binding")
public class DataBindingController {
    @InitBinder
    public void customBinding(WebDataBinder binder){
        binder.registerCustomEditor(Price.class, new PropertyEditorSupport(){
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                String[] parts = text.split(",");
                setValue(new Price(Double.valueOf(parts[0]), parts[1]));
            }
        });
    }
    @RequestMapping("/product-detail")
    public String getProductDetail(@RequestParam String name, @RequestParam int quantity, @RequestParam Price price){
        String productDescription = String.format("Product: %s | Quantity: %d | Cost: %s", name, quantity, price);
        return productDescription;
    }
}
