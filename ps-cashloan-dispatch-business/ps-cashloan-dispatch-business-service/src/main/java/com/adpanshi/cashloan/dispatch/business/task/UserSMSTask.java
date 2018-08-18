package com.adpanshi.cashloan.dispatch.business.task;

import com.adpanshi.cashloan.dispatch.run.bo.DispatchRunResponseBo;
import com.adpanshi.cashloan.dispatch.run.domain.Task;
import org.springframework.stereotype.Service;

/**
 * 用户短信原始数据抽取
 * Created by zsw on 2018/8/3 0003.
 */
@Service
public class UserSMSTask implements Task {
    @Override
    public DispatchRunResponseBo run(Integer taskInstanceId) {
        return null;
    }
}
