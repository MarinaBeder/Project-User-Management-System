package com.UserSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.UserSystem.config.JwtService;
import com.UserSystem.controller.dto.request.RegisterRequest;
import com.UserSystem.controller.dto.response.UserResponse;
import com.UserSystem.domain.Role;
import com.UserSystem.domain.User;
import com.UserSystem.exceptions.NonUniqueEmailException;
import com.UserSystem.repository.UserRepository;
import com.UserSystem.token.TokenRepository;

import jakarta.transaction.Transactional;

@Service
public class RegisterService {

	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepo;

	public RegisterService(PasswordEncoder passwordEncoder, UserRepository userRepo) {
		this.passwordEncoder = passwordEncoder;
		this.userRepo = userRepo;
	}

	public UserResponse createUser(RegisterRequest request) {
		System.out.println(request);

		User user = new User(request.getFullName(), request.getEmail(), passwordEncoder.encode(request.getPassword()),
				request.getAddress(), request.getAge(), request.getGender(), Role.USER

		);
		try {
			userRepo.save(user);

		} catch (DataIntegrityViolationException e) {
			throw new NonUniqueEmailException("Email is already Exist");
		}

		return new UserResponse(user.getId(), user.getFullName(), user.getEmail(), user.getAddress(), user.getAge(),
				user.getGender(), user.getRole(), user.getCreatedAt(), user.getUpdatedAt());

	}
}
