package com.springmvc.dao;

import com.springmvc.entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class ForgetPasswordDaoimpl implements ForgetPasswordDao{
    @Autowired
    private HibernateTemplate hibernateTemplate;
    @Autowired
    private SessionFactory sessionFactory;

@Override
    public boolean verifyEmailandDob(String email, String dob) {
        try {
            String hql = "FROM User WHERE email = :email AND dob = :dob";
            List<User> users = sessionFactory.openSession().createQuery(hql, User.class)
                    .setParameter("email", email)
                    .setParameter("dob", dob)
                    .list();

            return !users.isEmpty();
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Handle the exception appropriately in your application
        }
    }
    @Override
    @Transactional
    public void updatePassword(String email, String dob, String newPassword) {
        try {
            User user = (User) sessionFactory.getCurrentSession()
                    .createCriteria(User.class)
                    .add(Restrictions.eq("email", email))
                    .add(Restrictions.eq("dob", dob))
                    .uniqueResult();

            if (user != null) {
                user.setPassword(newPassword);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
