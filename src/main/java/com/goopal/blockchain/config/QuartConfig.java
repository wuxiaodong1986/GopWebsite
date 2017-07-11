package com.goopal.blockchain.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan("com.goopal.blockchain.quartz")
@EnableScheduling
public class QuartConfig 
{

}
