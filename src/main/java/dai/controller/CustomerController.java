package dai.controller;

import dai.model.Customer;
import dai.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    ICustomerService customerService;
    @GetMapping("/")
    public ModelAndView listCustomer(){
        List<Customer> customerList = customerService.findAll();
        ModelAndView modelAndView = new ModelAndView("/list");
        modelAndView.addObject("listCustomer", customerList);
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/add");
        modelAndView.addObject("createForm", new Customer());
        return modelAndView;
    }
    @PostMapping("/create/customer")
    public String createCustomer(@ModelAttribute("createForm")Customer customer){
        customerService.save(customer);
        return "redirect:/";
    }
    @GetMapping("/edit/{id}")
    public ModelAndView showFormEdit(@PathVariable Long id){
        Customer customer = customerService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/edit");
        modelAndView.addObject("editForm",customer);
        return modelAndView;
    }
    @PostMapping("/edit")
    public String editCustomer(@ModelAttribute("editForm")Customer customer){
        customerService.save(customer);
        return "redirect:/";
    }
    @GetMapping("/detail/{id}")
    public ModelAndView detailCustomer(@PathVariable Long id){
        Customer customer = customerService.findById(id);
        ModelAndView modelAndView =new ModelAndView("/detail");
        modelAndView.addObject("detailCustomer",customer);
        return modelAndView;
    }
    @GetMapping("/delete/{id}")
        public ModelAndView showFormDelete(@PathVariable Long id){
        Customer customer = customerService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/delete");
        modelAndView.addObject("deleteForm",customer);
        return modelAndView;
    }
    @PostMapping("/delete/customer")
    public String deleteCustomer(@ModelAttribute("deleteForm") Customer customer){
        System.out.println("customer ID"+customer.getId());
        customerService.delete(customer.getId());
        return "redirect:/";
    }

}
