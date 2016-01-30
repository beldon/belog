package belog.plugin;

import javax.annotation.PostConstruct;

public interface Plugin {

	@PostConstruct
	void init() throws Exception;
}
