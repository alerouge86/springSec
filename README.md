# springSec

SpringBoot + spring security:

 - usando "localhost:8080" -> visualizza home.html (questa pagina deve essere visibile per tutti)
 - dalla home, clicca su "firstPage" (anche questa deve essere visibile per tutti)
 - da firstPage.html, se faccio login (success) voglio rimanere in "firstPage.html" mentre ad oggi mi riporta in "/" (home.html). Questo viene fatto da 
 MySimpleUrlAuthenticationSuccessHandler.java - onAuthenticationSuccess() - determineTargetUrl() - redirectStrategy.sendRedirect().
 
 
 Ho usato mysql, nome db: "dbProva"
 puoi fare le 2 insert e l'alter table che trovi in "users.sql"
 
 
 utente (ROLE_USER):
 a@a.it
 
 pwd:
 123
