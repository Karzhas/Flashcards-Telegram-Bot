package kz.karzhas.camunda.delegates;

import kz.karzhas.services.FindFlashcardByGivenRandomSideService;
import kz.karzhas.telegram_bot.BotCommands;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UpdateRatingDelegate implements JavaDelegate {




    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

    }
}
