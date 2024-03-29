package com.example.demo.entity.xiehuan;


import java.time.LocalDateTime;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiex
 * @since 2020-08-02
 */
@Data
@Accessors(chain = true)
public class Information  {

    private static final long serialVersionUID = 1L;


    private String studentid;

    private String company;

    private LocalDateTime startdate;

    private LocalDateTime enddate;

    private String remark;

    private String status;

    private LocalDateTime statusdate;


}
