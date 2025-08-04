package springmvcdemo.controller;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import springmvcdemo.databinding.Price;

import java.beans.PropertyEditorSupport;

@RestController
@RequestMapping("/data-binding")
public class DataBindingController {

  @InitBinder
  public void customizeBinding(WebDataBinder binder) {
    binder.registerCustomEditor(
        Price.class,
        new PropertyEditorSupport() {
          @Override
          public void setAsText(String text) throws IllegalArgumentException {
            String[] splittedText = text.split(" ");
            setValue(new Price(Double.valueOf(splittedText[0]), splittedText[1]));
          }
        });
  }

  @GetMapping("/product")
  public String getProductDetails(
      @RequestParam("name") String name,
      @RequestParam("quantity") int quantity,
      @RequestParam("price") Price price) {
    String msg = String.format("Product %s [%s]: %s", name, quantity, price);

    return msg;
  }
}
