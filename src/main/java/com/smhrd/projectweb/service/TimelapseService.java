package com.smhrd.projectweb.service;

import com.smhrd.projectweb.mapper.TimelapseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TimelapseService {
    private final TimelapseMapper timelapseMapper;
}
