package messagequeue.modules.config.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("sys_connection_config")
public class ConfigEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 连接配置ID
     */
    @TableId
    private Long connectionId;
    /**
     * 连接配置编码
     */
    private String connectionCode;
    /**
     * 连接配置名称
     */
    private String connectionName;
    /**
     * 协议/方式
     */
    private String protocol;
    /**
     * 域名/IP/地址
     */
    private String domain;
    /**
     * 端口
     */
    private String port;
    /**
     * 参数1
     */
    private String arguments1;
    /**
     * 参数2
     */
    private String arguments2;
    /**
     * 参数3
     */
    private String arguments3;
    /**
     * 参数4
     */
    private String arguments4;
    /**
     * 参数5
     */
    private String arguments5;
    /**
     * 账号
     */
    private String username;
    /**
     * 密码/授权
     */
    private String password;
    /**
     * 备注
     */
    private String remark;
    /**
     * 自定义字段1
     */
    private String userItem1;
    /**
     * 自定义字段2
     */
    private String userItem2;
    /**
     * 自定义字段3
     */
    private String userItem3;
    /**
     * 自定义字段4
     */
    private String userItem4;
    /**
     * 自定义字段5
     */
    private String userItem5;
    /**
     * 更新人
     */
    private Long updateBy;
    /**
     * 更新日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
