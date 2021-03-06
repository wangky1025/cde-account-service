package io.cde.account.dao;


import io.cde.account.domain.Account;

/**
 * @author lcl
 */

public interface AccountDao {
    /**
     * 注册用户，存储用户信息.
     *
     * @param account 要注册的用户信息实体
     * @return 返回创建用户操作的结果，创建成功返回1，否则返回-1
     */
    int createAccount(Account account);

    /**
     * 根据id获取用户信息.
     *
     * @param id 用户id
     * @return 查询成功返回查询到的用户信息，否则返回null
     */
    Account findById(String id);

    /**
     * 根据用户名获取用户信息.
     *
     * @param name 用户名
     * @return 查询成功返回查询到的用户信息，否则返回null
     */
    Account findByName(String name);

    /**
     * 修改用户信息.
     *
     * @param account 需要修改的用户实体.
     * @return 返回修改操作的结果，修改成功返回1，否则返回-1
     */
    int updateAccount(Account account);

    /**
     * 修改用户名.
     *
     * @param accountId 用户id
     * @param name 需要修改的用户名
     * @return 返回修改操作的结果，修改成功返回1，否则返回-1
     */
    int updateName(String accountId, String name);

    /**
     * 修改用户密码.
     *
     * @param accountId 用户id
     * @param password  需要修改的密码
     * @return 返回修改操作的结果，修改成功返回1，否则返回-1
     */
    int updatePassword(String accountId, String password);
}
