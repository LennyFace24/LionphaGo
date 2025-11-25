package com.lionphago.backend.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.lionphago.backend.common.dto.UserDTO;

@Mapper
public interface UserMapper extends BaseMapper<UserDTO>{

}
