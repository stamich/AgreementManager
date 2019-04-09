package pl.codecity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.codecity.model.Agreement;
import pl.codecity.model.SystemDictionary;
import pl.codecity.service.AgreementService;
import pl.codecity.service.SystemDictionaryService;
import pl.codecity.service.SystemService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping(value = "/agreements")
public class AgreementController {

    Autowired
    public AgreementService agreementService;

    @Autowired
    public AgreementPeriodService agreementPeriodService;

    @Autowired
    public SystemService systemService;

    @Autowired
    public SystemDictionaryService systemDictionaryService;

    @Autowired
    public MessageSource messageSource;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String getAllAgreements(ModelMap modelMap){
        List<Agreement> agreements = agreementService.findAllAgreements();
        //List<AgreementPeriod> agreementPeriods = agreementPeriodService.findAll();
        List<SystemDictionary> systemDictionaries = systemDictionaryService.findAll();
        modelMap.addAttribute("agreements", agreements);
        modelMap.addAttribute("agreementPeriods", agreementPeriods);
        modelMap.addAttribute("systemDictionaries", systemDictionaries);
        return "allAgreements1";
    }

//    @RequestMapping(value = "/allData", method = RequestMethod.GET)
//    public String getAllAgreementsData(ModelMap modelMap){
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
//            systemsDTO.add(systemDTO);
//        }
//        modelMap.addAttribute("systems", systemsDTO);
//        return "allAgreements";
//    }

    //CREATE

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String newAgreement(ModelMap modelMap){
        Agreement agreement = new Agreement();
        //List<SystemDictionary> systemDictionaries = systemDictionaryService.findAll();
        modelMap.addAttribute("agreement", agreement);
        //modelMap.addAttribute("systemDictionaries", systemDictionaries);
        modelMap.addAttribute("edit", false);
        return "addAgreement";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveNewAgreement(Integer systemId, @Valid Agreement agreement, BindingResult bindingResult, ModelMap modelMap){

        if(bindingResult.hasErrors()){
            return "addAgreement";
        }

        if(!agreementService.isAgreementNumberUnique(agreement.getAgreementId(), agreement.getAgreementNumber())){
            FieldError agreementError = new FieldError("agreement", "agreementNumber", messageSource.
                    getMessage("non.unique.agreementNumber", new String[]{agreement.getAgreementNumber()}, Locale.getDefault()));
            bindingResult.addError(agreementError);
            return "addAgreement";
        }

        agreementService.saveAgreement(systemId, agreement);
        modelMap.addAttribute("agreementCreated", messageSource.
                getMessage("agreement.data.saved", new Integer[]{agreement.getAgreementId()}, Locale.getDefault()));
        return "dataSaved";
    }

    //UPDATE

    @RequestMapping(value = "/update{agreementId}", method = RequestMethod.GET)
    public String updateAgreement(@PathVariable Integer agreementId, ModelMap modelMap){
        Agreement agreement = agreementService.findAgreementById(agreementId);
        modelMap.addAttribute("agreement", agreement);
        modelMap.addAttribute("edit", true);
        return "addAgreement";
    }

    @RequestMapping(value = "/update{agreementId}", method = RequestMethod.POST)
    public String saveUpdatedAgreement(@Valid Agreement agreement, @PathVariable Integer systemId, ModelMap modelMap,
                                       BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "addAgreement";
        }

        if(!agreementService.isAgreementNumberUnique(agreement.getAgreementId(), agreement.getAgreementNumber())){
            FieldError agreementError = new FieldError("agreement", "agreementNumber", messageSource.
                    getMessage("non.unique.agreementNumber", new String[]{agreement.getAgreementNumber()}, Locale.getDefault()));
            bindingResult.addError(agreementError);
            return "addAgreement";
        }

        agreementService.saveAgreement(systemId, agreement);
        modelMap.addAttribute("agreementUpdated", messageSource.
                getMessage("agreement.data.updated", new Integer[]{agreement.getAgreementId()}, Locale.getDefault()));
        return "dataSaved";
    }

    //DEACTIVATE

    @RequestMapping(value = "/deactivate/{agreementId}", method = RequestMethod.GET)
    public String deactivateAgreement(@PathVariable Integer agreementId, ModelMap modelMap){
        Agreement agreement = agreementService.findAgreementById(agreementId);
        agreementService.deactivateAgreement(agreement);
        modelMap.addAttribute("agreement", agreement);
        return "agreementDeactivated";
    }

    //DELETE

    @RequestMapping(value = "/delete/{agreementId}", method = RequestMethod.GET)
    public String deleteAgreement(@PathVariable Integer agreementId){
        agreementService.deleteAgreement(agreementId);
        return "redirect:/allAgreements";
    }

    /**
     * This method will provide AgreementPeriod list to views
     */
    @ModelAttribute("periods")
    public List<AgreementPeriod> initializePeriods(){
        return agreementPeriodService.findAll();
    }

    /**
     * This method will provide SystemDictionary list to views
     */
    @ModelAttribute("dictionaries")
    public List<SystemDictionary> initializeDictionaries(){
        return systemDictionaryService.findAll();
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true, 10));
    }
}
