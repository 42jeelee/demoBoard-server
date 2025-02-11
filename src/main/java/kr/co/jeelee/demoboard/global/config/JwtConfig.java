package kr.co.jeelee.demoboard.global.config;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;

import kr.co.jeelee.demoboard.global.exception.custom.CustomException;
import kr.co.jeelee.demoboard.global.exception.custom.ErrorCode;

@Configuration
public class JwtConfig {

	private final RSAPublicKey publicKey;
	private final RSAPrivateKey privateKey;

	public JwtConfig() {
		KeyPair keyPair = generateKeyPair();

		this.publicKey = (RSAPublicKey) keyPair.getPublic();
		this.privateKey = (RSAPrivateKey) keyPair.getPrivate();
	}

	@Bean
	public JwtEncoder jwtEncoder() {
		RSAKey rsaKey = new RSAKey.Builder(this.publicKey)
			.privateKey(this.privateKey)
			.build();

		return new NimbusJwtEncoder(new ImmutableJWKSet<>(new JWKSet(rsaKey)));
	}

	@Bean
	public JwtDecoder jwtDecoder() {
		return NimbusJwtDecoder.withPublicKey(publicKey).build();
	}

	private KeyPair generateKeyPair() {
		try {
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			keyPairGenerator.initialize(2048);

			return keyPairGenerator.generateKeyPair();
		} catch (Exception e) {
			throw new CustomException(ErrorCode.KEYGEN_ERROR);
		}
	}

}
