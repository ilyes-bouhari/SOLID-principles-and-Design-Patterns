package com.tp2.journal;

import org.springframework.stereotype.Component;

@Component
public interface IJournal {
	void outPut_Msg(String message);
}
