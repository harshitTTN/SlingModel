package mymodel.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.time.*;
import java.util.Date;

@Model(adaptables = {Resource.class})
public class MyDetailModel {

    @Inject
    private String firstname;

    @Inject
    private String lastname;

    @Inject
    @Named("gender")
    private String gender;

    @Inject
    private Date dob;

    @Inject
    private String maritialstatus;


    public String getFirstname() {
        return firstname;
    }


    public String getLastname() {
        return lastname;
    }



    public String getGender() {
        return gender;
    }



    public Date getDob() {
        return dob;
    }


    public String getMaritialstatus() {
        return maritialstatus;
    }

    @PostConstruct
    public String getAge(){
        Instant instant = dob.toInstant();
        ZonedDateTime zone = instant.atZone(ZoneId.systemDefault());
        LocalDate givenDate = zone.toLocalDate();
        Period period = Period.between(givenDate,LocalDate.now());

        return period.getYears()+"";
    }


    @PostConstruct
    public String getHonorific(){
        if(gender.equals("m")){
            return "Mr.";
        }else if(gender.equals("f") && maritialstatus.equals("sin")){
            return "Ms.";
        }else
        {
            return "Mrs.";
        }
    }


}
