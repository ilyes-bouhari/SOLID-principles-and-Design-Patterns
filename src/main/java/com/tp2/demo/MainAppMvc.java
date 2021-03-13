package com.tp2.demo;


import javax.swing.SwingUtilities;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.tp2.di.AppConfig;
import com.tp2.etudiant.controlleurs.ControleurInscription;

@SpringBootApplication
public class MainAppMvc {

	
	public static void main(String[] args) {
        ConfigurableApplicationContext context = createApplicationContext(args);
        displayMainFrame(context);
    }

    private static ConfigurableApplicationContext createApplicationContext(String[] args) {
        return new SpringApplicationBuilder(AppConfig.class)
                .headless(false)
                .run(args);
    }

    private static void displayMainFrame(final ConfigurableApplicationContext context) {
        SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				ControleurInscription controleurInscription = context.getBean(ControleurInscription.class);
				controleurInscription.show();
			}
		});
	}
}