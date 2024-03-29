package com.bid.auction.domain.user.domain.entity;

import java.util.ArrayList;
import java.util.List;

import com.bid.auction.domain.payment.entity.Payment;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;

	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String password;
	@Column
	private String nickName;
	@Column
	private String email;
	@Column
	private String profileUrl;
	@Column
	private String phoneNum;
	@Column
	private String address;

	Role role;
	@OneToMany(mappedBy = "paidUser", cascade = CascadeType.ALL, orphanRemoval = true)
	@Builder.Default
	private List<Payment> userPayments = new ArrayList<>();
}
