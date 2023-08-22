package com.example.backend.controllers;

import com.example.backend.controllers.controller_requests.ReminderRequest;
import com.example.backend.controllers.controller_responses.FindFromUserResponse;
import com.example.backend.enums.ReminderEnum;
import com.example.backend.models.Reminder;
import com.example.backend.services.interfaces.ReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1.0/user/reminder/{userID}")
public class ReminderController {
    @Autowired
    private ReminderService reminderService;

    @GetMapping("get-all/{upID}")
    public FindFromUserResponse<Reminder> getAllReminders(@PathVariable Long upID,
                                                          @RequestParam int pageNo,
                                                          @RequestParam int pageSize,
                                                          @RequestParam(required = false) ReminderEnum reminderEnum) {
        return reminderService.getAllReminders(upID, pageNo, pageSize, reminderEnum);
    }

    @GetMapping("get-single/{remID}")
    public Reminder getSingleReminder(@PathVariable Long remID) {
        return reminderService.getSingleReminder(remID);
    }

    @PostMapping("{upID}")
    public String createReminder(@PathVariable Long upID,
                                 @RequestParam(required = false) Long appID,
                                 @RequestParam(required = false) Long preID,
                                 @RequestParam ReminderEnum reminderEnum,
                                 @RequestBody ReminderRequest reminderRequest) {
        return reminderService.createReminder(upID, appID, preID, reminderEnum, reminderRequest);
    }
    @PutMapping("{remID}")
    public String updateReminder(@PathVariable Long remID, @RequestBody ReminderRequest reminderRequest) {
        return reminderService.updateReminder(remID, reminderRequest);
    }
    @DeleteMapping("{remID}")
    public String deleteReminder(@PathVariable Long remID) {
        return reminderService.deleteReminder(remID);
    }
}
