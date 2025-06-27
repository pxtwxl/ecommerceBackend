package com.cart.ecom_proj.service;

import com.cart.ecom_proj.model.User;
import com.cart.ecom_proj.repo.UserRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @PersistenceContext
    private EntityManager entityManager;

    public User saveUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        System.out.println(user.getPassword());
        return repo.save(user);
    }

    public User updateUser(int id, User user) {
        User existingUser = repo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        if(user.getUsername() != null && !user.getUsername().isEmpty() && !user.getUsername().equals(existingUser.getUsername()))
            existingUser.setUsername(user.getUsername());

        if(user.getEmail() != null && !user.getEmail().isEmpty() && !user.getEmail().equals(existingUser.getEmail()))
            existingUser.setEmail(user.getEmail());

        existingUser.setRole(user.getRole());

        if(user.getAddress() != null && !user.getAddress().isEmpty() && !user.getAddress().equals(existingUser.getAddress()))
            existingUser.setAddress(user.getAddress());

        if(user.getPhone() != null && !user.getPhone().isEmpty() && !user.getPhone().equals(existingUser.getPhone()))
            existingUser.setPhone(user.getPhone());

        // Handle profile image fields like Product
        if (user.getProfileImg() != null && user.getProfileImg().length() > 0) {
            existingUser.setProfileImg(user.getProfileImg());
            existingUser.setProfileImgName(user.getProfileImgName());
            existingUser.setProfileImgType(user.getProfileImgType());
        }

        return repo.save(existingUser);
    }

    @Transactional
    public User updateUserProfileImage(int id, MultipartFile file) {
        User existingUser = repo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        try {
            // Convert file bytes to base64 string
            String base64Img = java.util.Base64.getEncoder().encodeToString(file.getBytes());
            existingUser.setProfileImg(base64Img);
            existingUser.setProfileImgName(file.getOriginalFilename());
            existingUser.setProfileImgType(file.getContentType());
        } catch (Exception e) {
            throw new RuntimeException("Failed to update profile image", e);
        }
        User savedUser = repo.save(existingUser);
        entityManager.flush();
        entityManager.clear();
        return savedUser;
    }

    public User fetchUser(int id) {
        return repo.findById(id).orElse(new User());
    }

    public int getUserId(String username) {
        return repo.findByUsername(username).getId();
    }
}
