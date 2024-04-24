package com.wedu.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wedu.common.utils.*;
import com.wedu.modules.sys.dao.SysLoginLogDao;
import com.wedu.modules.sys.entity.SysLoginLogEntity;
import com.wedu.modules.sys.service.SysLoginLogService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service("sysLoginLogService")
public class SysLoginLogServiceImpl extends ServiceImpl<SysLoginLogDao, SysLoginLogEntity> implements SysLoginLogService {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Autowired
    SysLoginLogDao sysLogLoginDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String) params.get("key");
        IPage<SysLoginLogEntity> page = this.page(
                new Query<SysLoginLogEntity>().getPage(params),
                new QueryWrapper<SysLoginLogEntity>()
                        .like(StringUtils.isNotBlank(key), "username", key)
                        .orderByDesc("LOGIN_TIME")
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryMyLoginPage(Map<String, Object> params) {
        String userName = (String) params.get("userName");
        IPage<SysLoginLogEntity> page = this.page(
                new Query<SysLoginLogEntity>().getPage(params),
                new QueryWrapper<SysLoginLogEntity>()
                        .eq(StringUtils.isNotBlank(userName), "username", userName)
                        .orderByDesc("LOGIN_TIME")
        );
        return new PageUtils(page);
    }

    @Override
    public Long findTotalVisitCount() {
        return sysLogLoginDao.findTotalVisitCount();
    }

    @Override
    public Long findTodayVisitCount() {
        return sysLogLoginDao.findTodayVisitCount(getToday(0), getToday(1));
    }

    @Override
    public Long findTodayIp() {
        return sysLogLoginDao.findTodayIp(getToday(0), getToday(1));
    }

    @Override
    public Map<String, Object> findLastSevenDaysVisitCount(String username) {
        Map<String, Object> res = Maps.newHashMap();
        List<String> days = Lists.newArrayList();
        List<Integer> totals = Lists.newArrayList();
        List<Integer> yours = Lists.newArrayList();
        for (int i = 10; i >= 0; i--) {
            LocalDate now = LocalDate.now().minusDays(i);

            String day = now.getYear()
                    + "-" + (String.valueOf(now.getMonthValue()).length() == 1 ? "0" + now.getMonthValue() : String.valueOf(now.getMonthValue()))
                    + "-" + (String.valueOf(now.getDayOfMonth()).length() == 1 ? "0" + now.getDayOfMonth() : String.valueOf(now.getDayOfMonth()));
            days.add(day);
            totals.add(sysLogLoginDao.getNumByTime(formatDateTime(day + " 00:00:00"), formatDateTime(day + " 23:59:59"), null));
            yours.add(sysLogLoginDao.getNumByTime(formatDateTime(day + " 00:00:00"), formatDateTime(day + " 23:59:59"), username));
        }
        res.put("day", days);
        res.put("total", totals);
        res.put("your", yours);
        return res;
    }

    @Override
    public void saveLoginLog(SysLoginLogEntity loginLog) {
        loginLog.setLoginTime(new Date());
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        String ip = IPUtils.getIpAddr(request);
        loginLog.setIp(ip);
        this.save(loginLog);
    }

    public Date getToday(int type) {
        Calendar calendar = Calendar.getInstance();
        if (type == 0) {
            calendar.setTime(new Date());
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            return calendar.getTime();
        } else {
            calendar.setTime(new Date());
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            return calendar.getTime();
        }
    }

    public LocalDateTime formatDateTime(String timeString) {
        return LocalDateTime.parse(timeString, DATE_TIME_FORMATTER);
    }

}
