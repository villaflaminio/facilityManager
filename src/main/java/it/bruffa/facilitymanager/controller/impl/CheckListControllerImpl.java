package it.bruffa.facilitymanager.controller.impl;

import it.bruffa.facilitymanager.controller.CheckListController;
import it.bruffa.facilitymanager.service.CheckListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckListControllerImpl implements CheckListController {
    @Autowired
    private CheckListService checkListService;
}
