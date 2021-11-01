package com.example.demo.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	//認証の設定
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		//インメモリ認証
		auth
			.inMemoryAuthentication()
				.withUser("user")
				.password(passwordEncoder().encode("user"))
				.roles("USER");
	}
	
	//セキュリティの対象外を設定
	@Override
	public void configure(WebSecurity web) throws Exception {
		
		//セキュリティを適用しない
		web
			.ignoring()
				.antMatchers("/webjars/**")
				.antMatchers("/css/**")
				.antMatchers("/js/**");
	}
	
	//セキュリティの各種を設定
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		//ログイン不要ページの設定
		http
			.authorizeRequests()
				.antMatchers("/login").permitAll() //直リンクOK
				.anyRequest().authenticated(); //それ以外は直リンクNG
			
		//ログイン処理
		http
			.formLogin()
				.loginProcessingUrl("/login") //ログイン処理のパス
				.loginPage("/login") //ログインページの指定、この指定によりSpringSecurityのデフォルトが表示されなくなる
				.failureUrl("/login") //ログイン失敗時の遷移先
				.usernameParameter("userId") //ログインページのユーザーID
				.passwordParameter("password") //ログインページのパスワード
				.defaultSuccessUrl("/home", true); //成功後の遷移先
			
			//CSRF対策を無効に設定（一時的）
			http.csrf().disable();
		
		//ログアウト処理
		http
			.logout()
			
				//ログアウトのリクエスト先パスを設定、HTTLファイル内のth:action="@{/logout}"のパスと、
				//このメソッドの設定値を一致させ、POSTメソッドでログアウトさせる
				.logoutUrl("/logout") 
				.invalidateHttpSession(true) //ログアウト時のセッション破棄を有効化
				.deleteCookies("JSESSIONID") //ログアウト時に削除するクッキー名
				.logoutSuccessUrl("/login"); //ログアウト成功時の遷移先
	}
	
	//インメモリ認証ユーザーのパスワードを暗号化
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		//パスワードをハッシュ化する
		return new BCryptPasswordEncoder();	
	}
}
