package com.example.EmployeeManagement.EmployeeManagement.Service;

import com.example.EmployeeManagement.EmployeeManagement.CommonValidatons.Common;
import com.example.EmployeeManagement.EmployeeManagement.Entity.User;
import com.example.EmployeeManagement.EmployeeManagement.Exceptions.EmptyRecordException;
import com.example.EmployeeManagement.EmployeeManagement.Repository.UserRepo;
import jakarta.transaction.Transactional;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class
UserService
{
    @Autowired
    UserRepo repo;

    Common com = new Common();

    public String createUser(User user) throws IllegalArgumentException
    {
        Optional<User> existingUserByEmail = repo.findByEmail(user.getEmail());
        if(com.isEmailValidated(user.getEmail()))
        {
            if (existingUserByEmail.isPresent()) {
                throw new IllegalArgumentException("Email is already in use");
            }
            else
            {
                Optional<User> existingUserByPhone = repo.findByPhone(user.getPhone());
                if(com.isPhoneValid(user.getPhone()))
                {
                    if (existingUserByPhone.isPresent())
                    {
                        return "Phone number is already in use";
                    }
                    else if(!com.isPinCodeValid(user.getPincode()))
                    {
                        return "Invalid PinCode provided!";
                    }
                    repo.save(user);
                }
                else
                {
                    return "Invalid Phone Number provided!";
                }
            }
        }
        else
        {
            return "Invalid Email Address provided!";
        }
        return null;
    }

    public String getAllUser(User user)
    {
        List<User> getAll = repo.findAll();
        if (getAll.isEmpty())
        {
            return "Record is empty!";
        }
        else
        {
            StringBuilder userDetails = new StringBuilder();
            for (User e : getAll) {
                userDetails.append("User ID: ").append(e.getUser_id())
                        .append(", Name: ").append(e.getFirst_name()).append(" ").append(e.getLast_name())
                        .append(", Email: ").append(e.getEmail())
                        .append(", Phone: ").append(e.getPhone())
                        .append(", DOB: ").append(e.getDob())
                        .append(", Address: ").append(e.getAddress())
                        .append(", City: ").append(e.getCity())
                        .append(", State: ").append(e.getState())
                        .append(", Pincode: ").append(e.getPincode())
                        .append(".\n");
            }
            return userDetails.toString();
        }
    }

    public Optional<User> getUserById(Long user_id)
    {
        Optional<User> getUser = repo.findById(user_id);
        if (getUser.isEmpty())
        {
            throw new EmptyRecordException("Record is empty!");
        }
        else
        {
            return getUser;
        }
    }

    public Optional<User> getUserByEmail(String email)
    {
        Optional<User> getUser = repo.findByEmail(email);
        if (getUser.isEmpty())
        {
            throw new EmptyRecordException("Record is empty!");
        }
        else
        {
            return getUser;
        }
    }

    public Optional<User> getUserByPhone(String phone)
    {
        Optional<User> getUser = repo.findByPhone(phone);
        if (getUser.isEmpty())
        {
            throw new EmptyRecordException("Record is empty!");
        }
        else
        {
            return getUser;
        }
    }

    public Optional<User> updateUser(User updatedUser, Long user_Id) {
        Optional<User> existingUserGet = repo.findById(user_Id);
        if (existingUserGet.isPresent()) {
            User existingUser = existingUserGet.get();
            String first_name = existingUser.getFirst_name();
            String last_name = existingUser.getLast_name();
            String email = existingUser.getEmail();
            String password = existingUser.getPassword();
            String dob = existingUser.getDob();
            String phone = existingUser.getPhone();
            String address = existingUser.getAddress();
            String state = existingUser.getState();
            String city = existingUser.getCity();
            String pincode = existingUser.getPincode();
            if (updatedUser.getFirst_name() != null) {
                existingUser.setFirst_name(updatedUser.getFirst_name());
            }
            else
            {
                existingUser.setFirst_name(first_name);
            }
            if (updatedUser.getLast_name() != null) {
                existingUser.setLast_name(updatedUser.getLast_name());
            }
            else
            {
                existingUser.setLast_name(last_name);
            }
            if (updatedUser.getDob() != null) {
                existingUser.setDob(updatedUser.getDob());
            }
            else
            {
                existingUser.setDob(dob);
            }
            if (updatedUser.getEmail() != null) {
                existingUser.setEmail(updatedUser.getEmail());
            }
            else
            {
                existingUser.setEmail(email);
            }
            if (updatedUser.getPassword() != null) {
                existingUser.setPassword(updatedUser.getPassword());
            }
            else
            {
                existingUser.setPassword(password);
            }
            if (updatedUser.getPhone() != null) {
                existingUser.setPhone(updatedUser.getPhone());
            }
            else
            {
                existingUser.setPhone(phone);
            }
            if (updatedUser.getAddress() != null) {
                existingUser.setAddress(updatedUser.getAddress());
            }
            else
            {
                existingUser.setAddress(address);
            }
            if (updatedUser.getCity() != null) {
                existingUser.setCity(updatedUser.getCity());
            }
            else
            {
                existingUser.setCity(city);
            }
            if (updatedUser.getState() != null) {
                existingUser.setState(updatedUser.getState());
            }
            else
            {
                existingUser.setState(state);
            }
            if (updatedUser.getPincode() != null) {
                existingUser.setPincode(updatedUser.getPincode());
            }
            else
            {
                existingUser.setPincode(pincode);
            }

            repo.save(existingUser);

            return Optional.of(existingUser);
        } else {
            return Optional.empty();
        }
    }

    public String deleteUserById(Long user_id)
    {
       Optional<User> getUser = repo.findById(user_id);
       String name = " "+getUser.get().getFirst_name()+" "+getUser.get().getLast_name()+" ";
       String email = getUser.get().getEmail();
       if(getUser.isEmpty())
       {
           throw new EmptyRecordException("No record found with the ID = "+user_id);
       }
       else
       {
           repo.deleteById(user_id);
           return "User: "+name+" with Email: "+email+" has been successfully removed from database!";
       }
    }

    public String deleteAllUser()
    {
        repo.deleteAll();
        return "All records cleared!";
    }
}