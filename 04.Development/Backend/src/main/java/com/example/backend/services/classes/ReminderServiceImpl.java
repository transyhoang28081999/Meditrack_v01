package com.example.backend.services.classes;

import com.example.backend.controllers.controller_requests.ReminderRequest;
import com.example.backend.controllers.controller_responses.FindFromUserResponse;
import com.example.backend.enums.ReminderEnum;
import com.example.backend.generics.Pagination;
import com.example.backend.models.*;
import com.example.backend.repositories.*;
import com.example.backend.securities.user.User;
import com.example.backend.securities.user.UserRepository;
import com.example.backend.services.interfaces.ReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReminderServiceImpl implements ReminderService {
    @Autowired
    private ReminderRepository reminderRepository;
    @Autowired
    private RemindAppointmentRepository remindAppointmentRepository;
    @Autowired
    private TakeMedicineRepository takeMedicineRepository;
    @Autowired
    private OtherRepository otherRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private PrescriptionRepository prescriptionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserProfileRespository userProfileRespository;

    // Cac ham xu ly getAllreminders:
    private List<Reminder> remindAppointments (Long upID) {
        return reminderRepository.findAllByUserProfileIDForRemindAppointment(upID);
    }
    private List<Reminder> takeMedicines (Long upID) {
        return reminderRepository.findAllByUserProfileIDForTakeMedicine(upID);
    }
    private List<Reminder> others(Long upID) {
        return reminderRepository.findAllByUserProfileIDForOther(upID);
    }
    @Override
    public FindFromUserResponse<Reminder> getAllReminders(Long upID, int pageNo, int pageSize, ReminderEnum reminderEnum) {
        List<Reminder> allReminders;
        if(reminderEnum == ReminderEnum.REMIND_APPOINTMENTS){
            allReminders = this.remindAppointments(upID);
        }
        else if(reminderEnum == ReminderEnum.TAKE_MEDICINES){
            allReminders = this.takeMedicines(upID);
        }
        else if(reminderEnum == ReminderEnum.OTHERS) {
            allReminders = this.others(upID);
        }
        else if(reminderEnum == null) {
            allReminders = new ArrayList<>();
            allReminders.addAll(this.remindAppointments(upID));
            allReminders.addAll(this.takeMedicines(upID));
            allReminders.addAll(this.others(upID));
            // Sap xep lai theo datetime
            Collections.sort(allReminders, new Comparator<Reminder>() {
                @Override
                public int compare(Reminder o1, Reminder o2) {
                    return o2.getRemDatetime().compareTo(o1.getRemDatetime());
                }
            });
        } else {
            return null;
        }

        Pagination<Reminder> pagination = new Pagination<>();
        List<Reminder> reminders = pagination.pagination(allReminders, pageNo, pageSize);
        return new FindFromUserResponse<>(
                reminders,
                pageNo,
                pageSize,
                Math.floorDiv(allReminders.size(), pageSize) + 1,
                allReminders.size());
    }

    @Override
    public Reminder getSingleReminder(Long remID) {
        return reminderRepository.findById(remID).orElseThrow();
    }

    @Override
    public String createReminder(Long upID, Long appID, Long preID, ReminderEnum reminderEnum, ReminderRequest reminderRequest) throws NoSuchElementException {
        Reminder reminder = new Reminder();
        reminder.setRemDatetime(reminderRequest.getDateTime());
        reminder.setRemMessage(reminderRequest.getMessage());
        reminder.setRemType(reminderEnum);

        reminderRepository.save(reminder);

        if(reminderEnum == ReminderEnum.REMIND_APPOINTMENTS) {
            Appointment appointment = appointmentRepository.findById(appID).orElseThrow();

            RemindAppointment remindAppointment = new RemindAppointment();
            remindAppointment.setReminder(reminder);
            remindAppointment.setAppointment(appointment);
            remindAppointmentRepository.save(remindAppointment);
            return "Successfully!";
        }
        if(reminderEnum == ReminderEnum.TAKE_MEDICINES) {
            Prescription prescription = prescriptionRepository.findById(preID).orElseThrow();

            TakeMedicine takeMedicine = new TakeMedicine();
            takeMedicine.setReminder(reminder);
            takeMedicine.setPrescription(prescription);
            takeMedicineRepository.save(takeMedicine);
            return "Successfully!";
        }
        if(reminderEnum == ReminderEnum.OTHERS) {
            UserProfile userProfile = userProfileRespository.findByUpID(upID).orElseThrow();

            Other other = new Other();
            other.setReminder(reminder);
            other.setUserProfile(userProfile);

            otherRepository.save(other);
            return "Successfully!";
        }
        return "You have some wrong!";
    }

    @Override
    public String updateReminder(Long remID, ReminderRequest reminderRequest) throws NoSuchElementException {
        Reminder reminder = reminderRepository.findByRemID(remID).orElseThrow();
        reminder.setRemDatetime(reminderRequest.getDateTime());
        reminder.setRemMessage(reminderRequest.getMessage());
        reminderRepository.save(reminder);
        return "Successfully!";
    }

    @Override
    public String deleteReminder(Long remID) {
        reminderRepository.deleteById(remID);
        return "Delete successfully!";
    }
}
