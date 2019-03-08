package com.hz.wsnIndoorBack.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hz.wsnIndoorBack.DTO.Result;
import com.hz.wsnIndoorBack.DTO.Trail;
import com.hz.wsnIndoorBack.mapper.TargetMapper;
import com.hz.wsnIndoorBack.model.Target;
import com.hz.wsnIndoorBack.model.TrailPoint;
import com.hz.wsnIndoorBack.service.ITargetService;

@Service
public class TargetServiceImpl implements ITargetService {
	@Autowired
	private TargetMapper targetMapper;
	
	@Override
	public Result<List<Target>>getTargetByNidAndTime(int nid,String startTime,String endTime){
		Result<List<Target>> result = new Result<>();
		List<Target> targets = targetMapper.getTargetsByNidAndTime(nid,
				startTime, endTime);
		result.setObj(targets);
		return result;
	}
	

	public Result<List<Trail>> getTrailsByTargetId(Integer targetId) {
		Result<List<Trail>> result = new Result<>();
		List<TrailPoint> tps = targetMapper
				.getTrailPointsByTargetId(targetId);
		String trailId = "";
		List<Trail>list=new ArrayList<>();
		
		Trail trailHead = new Trail();
		Trail trail=trailHead;
		for (TrailPoint tp : tps) {
			if (!trailId.equals(tp.getTrailId())) {
				if (trail!=trailHead){
					list.add(trail);
				}
				trailId = tp.getTrailId();
				trail = new Trail(tp.getTargetId(), tp.getTrailId(),
						tp.getNid());
				trail.setStartTime(tp.getCreateTime());
			}

			trail.setEndTime(tp.getCreateTime());
			Float[] point = new Float[] { tp.getX(), tp.getY() };
			trail.getPoints().add(point);
		}
		list.add(trail);
		result.setObj(list);
		return result;
	}

}
