package pl.codecity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.codecity.model.SystemDictionary;
import pl.codecity.service.AgreementService;
import pl.codecity.service.ClientService;
import pl.codecity.service.SystemDictionaryService;
import pl.codecity.service.SystemService;

import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping(value = "/systems")
public class SystemController {

    @Autowired
    private SystemService systemService;

    @Autowired
    private SystemDictionaryService systemDictionaryService;

    @Autowired
    private AgreementService agreementService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private MessageSource messageSource;

//    @RequestMapping(value = "/all")
//    public String getAll(ModelMap modelMap){
//        List<System> systems = systemService.findAllSystems();
//        List<SystemDTO> systemsDTO = new ArrayList<SystemDTO>();
//        for (System system: systems){
//            SystemDTO systemDTO = new SystemDTO();
//            systemDTO.setSystemId(system.getSystemId());
//            systemDTO.setSystemDescription(system.getSystemDescription());
//            systemDTO.setTechnologyDescription(system.getTechnologyDescription());
//            systemDTO.setComments(system.getComments());
//            systemDTO.setSystemDictionaries(systemDictionaryService.findAll(system.getSystemId()));
//            systemDTO.setAgreements(agreementService.findAllAgreements(system.getSystemId()));
//            systemDTO.setClients(clientService.findAllClients(system.getSystemId()));
//            systemsDTO.add(systemDTO);
//        }
//        modelMap.addAttribute("systems", systemsDTO);
//        return "allSystems";
//    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String newSystem(ModelMap modelMap){
        System system = new System();
        List<SystemDictionary> systemDictionaries = systemDictionaryService.findAll();
        modelMap.addAttribute("system", system);
        modelMap.addAttribute("systemDictionaries", systemDictionaries);
        modelMap.addAttribute("edit", false);
        return "addSystem";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveNewSystem(@Valid System system, ModelMap modelMap, BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            return "addSystem";
        }

        systemService.saveSystem(system);
        modelMap.addAttribute("systemCreated", messageSource.getMessage("system.data.saved",
                new Integer[]{system.getSystemId()}, Locale.getDefault()));
        return "dataSaved";
    }

    @RequestMapping(value = "/update/{systemId}", method = RequestMethod.GET)
    public String updateSystem(@PathVariable Integer systemId, ModelMap modelMap){
        System system = systemService.findSystemById(systemId);
        modelMap.addAttribute("system", system);
        modelMap.addAttribute("edit", true);
        return "addSystem";
    }

    @RequestMapping(value = "/update/{systemId}", method = RequestMethod.POST)
    public String saveUpdatedSystem(@Valid System system, @PathVariable Integer systemId, ModelMap modelMap, BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            return "addSystem";
        }

        systemService.saveSystem(system);
        modelMap.addAttribute("systemCreated", messageSource.getMessage("system.data.updated",
                new Integer[]{system.getSystemId()}, Locale.getDefault()));
        return "dataSaved";
    }

    @RequestMapping(value = "/delete/{systemId}", method = RequestMethod.GET)
    public String deleteSystem(@PathVariable Integer systemId){
        systemService.deleteSystem(systemId);
        return "redirect:/allSystems";
    }

    /**
     * This method will provide SystemDictionary list to views
     */
    @ModelAttribute("dictionaries")
    public List<SystemDictionary> initializeDictionaries(){
        return systemDictionaryService.findAll();
    }
}
