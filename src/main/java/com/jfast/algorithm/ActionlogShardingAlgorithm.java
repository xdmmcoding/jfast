package com.jfast.algorithm;

import java.util.Collection;
import java.util.Date;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import com.jfast.util.DateUtil;

public class ActionlogShardingAlgorithm implements PreciseShardingAlgorithm<Date> {

	@Override
	public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Date> shardingValue) {
		Date posttime = (Date) shardingValue.getValue();
        String index = DateUtil.formatDate(posttime, "yyyyMMdd");
        String tableReal = shardingValue.getLogicTableName().concat("_").concat(index);
        return tableReal;
	}
}