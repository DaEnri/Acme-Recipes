package acme.features.spam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.spam.SpamDetector;
import acme.system.configuration.SystemConfiguration;

@Service
public class SpamDetectorService {

	@Autowired
	SpamDetectorRepository repository;
	
	public boolean isTextSpam(final String text) {
		
		SystemConfiguration sc;
		
		sc = this.repository.getSystemConfiguration();
		
		SpamDetector sd;
		
		sd = new SpamDetector(sc.getSpamTerms(), sc.getSpamThreshold());
		
		return sd.isTextSpamChecker(text);
		
	}
	
}
