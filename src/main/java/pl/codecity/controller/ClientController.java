package pl.codecity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.codecity.model.Client;
import pl.codecity.service.ClientService;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

/**
 * @author Michal Stawarski
 */
@Controller
@RequestMapping(value = "/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String getAllClients(ModelMap modelMap){
        List<Client> clients = clientService.findAll();
        modelMap.addAttribute("clients", clients);
        return "allClients";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String newClient(Integer systemId, ModelMap modelMap){
        Client client = new Client();
        modelMap.addAttribute("client", client);
        modelMap.addAttribute("systemId", systemId);
        modelMap.addAttribute("edit", false);
        return "addClient";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveNewClient(@Valid Client client,Integer systemId, ModelMap modelMap, BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            return "addClient";
        }

        if(!clientService.isClientNameUnique(client.getClientId(), client.getClientName())){
            FieldError clientError = new FieldError("client", "clientName", messageSource.getMessage("non.unique.clientName",
                    new String[]{client.getClientName()}, Locale.getDefault()));
            return "addClient";
        }
        clientService.saveClient(systemId, client);
        modelMap.addAttribute("clientCreated", messageSource.getMessage("client.data.saved",
                new Integer[]{client.getClientId()}, Locale.getDefault()));
        return "dataSaved";
    }

    @RequestMapping(value = "/update/{clientId}", method = RequestMethod.GET)
    public String updateClient(@PathVariable Integer clientId, ModelMap modelMap){
        Client client = clientService.findClientById(clientId);
        modelMap.addAttribute("client", client);
        modelMap.addAttribute("edit", true);
        return "addClient";
    }

    @RequestMapping(value = "/update/{clientId}", method = RequestMethod.POST)
    public String saveUpdatedClient(@Valid Client client, @PathVariable Integer clientId, Integer systemId, ModelMap modelMap, BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            return "addClient";
        }

        if(!clientService.isClientNameUnique(client.getClientId(), client.getClientName())){
            FieldError clientError = new FieldError("client", "clientName", messageSource.getMessage("non.unique.clientName",
                    new String[]{client.getClientName()}, Locale.getDefault()));
            return "addClient";
        }
        clientService.saveClient(systemId, client);
        modelMap.addAttribute("clientCreated", messageSource.getMessage("client.data.updated",
                new Integer[]{client.getClientId()}, Locale.getDefault()));
        return "dataSaved";
    }

    //DELETE

    @RequestMapping(value = "/delete/{clientId}", method = RequestMethod.GET)
    public String deleteClient(@PathVariable Integer clientId){
        clientService.deleteClient(clientId);
        return "redirect:/allClients";
    }
}
