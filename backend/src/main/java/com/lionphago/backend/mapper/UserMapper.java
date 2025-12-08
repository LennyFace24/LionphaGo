package com.lionphago.backend.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lionphago.backend.common.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

/**
 * UserDTO 的 Mapper
 * </br>
 * 对应 user_info 表
 */
@Mapper
public interface UserMapper extends BaseMapper<UserDTO>{

}
