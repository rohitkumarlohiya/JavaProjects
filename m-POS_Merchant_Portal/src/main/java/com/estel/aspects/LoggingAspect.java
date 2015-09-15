package com.estel.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;


@Aspect
public class LoggingAspect {

    /*@Before("execution(* com.estel.service.StudentService.addStudent(..))")
     public void logBefore(JoinPoint joinPoint) {

         System.out.println("logBefore() is running!");
         System.out.println("hijacked : " + joinPoint.getSignature().getName());
         System.out.println("******");

         Object[] signatureArgs = joinPoint.getArgs();
            for (Object signatureArg: signatureArgs) {
               System.out.println("Arg: " + signatureArg);
         }

     }*/


    /*@Around("execution(* com.estel.service.*.*(..))")
        public Object logMethod(ProceedingJoinPoint joinPoint) throws Throwable{
            final Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass().getName());
            Object retVal = null;

            try {
                StringBuffer startMessageStringBuffer = new StringBuffer();

                startMessageStringBuffer.append("Start method ");
                startMessageStringBuffer.append(joinPoint.getSignature().getName());
                startMessageStringBuffer.append("(");

                Object[] args = joinPoint.getArgs();
                for (int i = 0; i < args.length; i++) {
                    startMessageStringBuffer.append(args[i]).append(",");
                }
                if (args.length > 0) {
                    startMessageStringBuffer.deleteCharAt(startMessageStringBuffer.length() - 1);
                }

                startMessageStringBuffer.append(")");

                logger.trace(startMessageStringBuffer.toString());
                System.out.println(startMessageStringBuffer.toString());

                StopWatch stopWatch = new StopWatch();
                stopWatch.start();

                retVal = joinPoint.proceed();

                stopWatch.stop();

                StringBuffer endMessageStringBuffer = new StringBuffer();
                endMessageStringBuffer.append("Finish method ");
                endMessageStringBuffer.append(joinPoint.getSignature().getName());
                endMessageStringBuffer.append("(..); execution time: ");
                endMessageStringBuffer.append(stopWatch.getTotalTimeMillis());
                endMessageStringBuffer.append(" ms;");

                logger.trace(endMessageStringBuffer.toString());
                System.out.println(endMessageStringBuffer.toString());
            } catch (Throwable ex) {
                StringBuffer errorMessageStringBuffer = new StringBuffer();

                 // Create error message
                 logger.error(errorMessageStringBuffer.toString(), ex);
                 System.out.println(errorMessageStringBuffer.toString());

                throw ex;
            }

            return retVal;
        }
    */
    @Around("execution(* com.estel.dao.*.*(..))")
    public Object logMethod2(ProceedingJoinPoint joinPoint) throws Throwable {
        final Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass().getName());

        Object retVal = null;

        try {
            StringBuffer startMessageStringBuffer = new StringBuffer();

            startMessageStringBuffer.append("Start method ");
            startMessageStringBuffer.append(joinPoint.getSignature().getName());
            startMessageStringBuffer.append("(");

            Object[] args = joinPoint.getArgs();
            for (int i = 0; i < args.length; i++) {
                startMessageStringBuffer.append(args[i]).append(",");
            }
            if (args.length > 0) {
                startMessageStringBuffer.deleteCharAt(startMessageStringBuffer.length() - 1);
            }

            startMessageStringBuffer.append(")");

            logger.info(startMessageStringBuffer.toString());
            //System.out.println(startMessageStringBuffer.toString());

            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            retVal = joinPoint.proceed();

            stopWatch.stop();

            StringBuffer endMessageStringBuffer = new StringBuffer();
            endMessageStringBuffer.append("Finish method ");
            endMessageStringBuffer.append(joinPoint.getSignature().getName());
            endMessageStringBuffer.append("(..); execution time: ");
            endMessageStringBuffer.append(stopWatch.getTotalTimeMillis());
            endMessageStringBuffer.append(" ms;");

            logger.info(endMessageStringBuffer.toString());
            //System.out.println(endMessageStringBuffer.toString());
        } catch (Throwable ex) {
            StringBuffer errorMessageStringBuffer = new StringBuffer();

            // Create error message
            logger.info(errorMessageStringBuffer.toString(), ex);
            //System.out.println(errorMessageStringBuffer.toString());

            throw ex;
        }

        return retVal;
    }


    @Around("execution(* com.estel.service.EmailService.*(..))")
    public Object logMethod3(ProceedingJoinPoint joinPoint) throws Throwable {
        final Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass().getName());

        Object retVal = null;

        try {
            StringBuffer startMessageStringBuffer = new StringBuffer();

            startMessageStringBuffer.append("Start method ");
            startMessageStringBuffer.append(joinPoint.getSignature().getName());
            startMessageStringBuffer.append("(");

            Object[] args = joinPoint.getArgs();
            for (int i = 0; i < args.length; i++) {
                startMessageStringBuffer.append(args[i]).append(",");
            }
            if (args.length > 0) {
                startMessageStringBuffer.deleteCharAt(startMessageStringBuffer.length() - 1);
            }

            startMessageStringBuffer.append(")");

            logger.info(startMessageStringBuffer.toString());
            //System.out.println(startMessageStringBuffer.toString());

            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            retVal = joinPoint.proceed();

            stopWatch.stop();

            StringBuffer endMessageStringBuffer = new StringBuffer();
            endMessageStringBuffer.append("Finish method ");
            endMessageStringBuffer.append(joinPoint.getSignature().getName());
            endMessageStringBuffer.append("(..); execution time: ");
            endMessageStringBuffer.append(stopWatch.getTotalTimeMillis());
            endMessageStringBuffer.append(" ms;");

            logger.info(endMessageStringBuffer.toString());
            //System.out.println(endMessageStringBuffer.toString());
        } catch (Throwable ex) {
            StringBuffer errorMessageStringBuffer = new StringBuffer();

            // Create error message
            logger.info(errorMessageStringBuffer.toString(), ex);
            //System.out.println(errorMessageStringBuffer.toString());

            throw ex;
        }

        return retVal;
    }


    @Around("execution(* com.estel.service.SmsService.*(..))")
    public Object logMethod4(ProceedingJoinPoint joinPoint) throws Throwable {
        final Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass().getName());

        Object retVal = null;

        try {
            StringBuffer startMessageStringBuffer = new StringBuffer();

            startMessageStringBuffer.append("Start method ");
            startMessageStringBuffer.append(joinPoint.getSignature().getName());
            startMessageStringBuffer.append("(");

            Object[] args = joinPoint.getArgs();
            for (int i = 0; i < args.length; i++) {
                startMessageStringBuffer.append(args[i]).append(",");
            }
            if (args.length > 0) {
                startMessageStringBuffer.deleteCharAt(startMessageStringBuffer.length() - 1);
            }

            startMessageStringBuffer.append(")");

            logger.info(startMessageStringBuffer.toString());
            //System.out.println(startMessageStringBuffer.toString());

            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            retVal = joinPoint.proceed();

            stopWatch.stop();

            StringBuffer endMessageStringBuffer = new StringBuffer();
            endMessageStringBuffer.append("Finish method ");
            endMessageStringBuffer.append(joinPoint.getSignature().getName());
            endMessageStringBuffer.append("(..); execution time: ");
            endMessageStringBuffer.append(stopWatch.getTotalTimeMillis());
            endMessageStringBuffer.append(" ms;");

            logger.info(endMessageStringBuffer.toString());
            //System.out.println(endMessageStringBuffer.toString());
        } catch (Throwable ex) {
            StringBuffer errorMessageStringBuffer = new StringBuffer();

            // Create error message
            logger.info(errorMessageStringBuffer.toString(), ex);
            //System.out.println(errorMessageStringBuffer.toString());

            throw ex;
        }

        return retVal;
    }



    /*@Before("execution(* com.estel.service.ContactService.addContact(..))")
     public void logBefore(JoinPoint joinPoint) {

         System.out.println("logBefore() is running!");
         System.out.println("hijacked : " + joinPoint.getSignature().getName());
         System.out.println("******");
     }

     @After("execution(* com.estel.service.ContactService.addContact(..))")
     public void logAfter(JoinPoint joinPoint) {

         System.out.println("logAfter() is running!");
         System.out.println("hijacked : " + joinPoint.getSignature().getName());
         System.out.println("******");
     }

     @AfterReturning(
               pointcut = "execution(* com.estel.service.ContactService.listContact(..))",
               returning= "result")
     public void logAfterReturning(JoinPoint joinPoint, Object result) {

         System.out.println("logAfterReturning() is running!");
         System.out.println("hijacked : " + joinPoint.getSignature().getName());
         System.out.println("Method returned value is : " + result);
         System.out.println("******");

 //		for (Contact res : (Collection<Contact>)result) {
 //			System.out.println(res.getId());
 //			System.out.println(res.getFirstname());
 //			System.out.println(res.getEmail());
 //		}
     }*/

    /*@AfterThrowing(
               pointcut = "execution(* com.estel.service.ContactService.listContact(..))",
               throwing= "error")
     public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {

             System.out.println("logAfterThrowing() is running!");
             System.out.println("hijacked : " + joinPoint.getSignature().getName());
             System.out.println("Exception : " + error);
             System.out.println("******");

     }

     @Around("execution(* com.estel.service.ContactService.listContact(..))")
     public void logAround(ProceedingJoinPoint joinPoint) throws Throwable {

         System.out.println("logAround() is running!");
         System.out.println("hijacked method : " + joinPoint.getSignature().getName());
         System.out.println("hijacked arguments : " + Arrays.toString(joinPoint.getArgs()));

         System.out.println("Around before is running!");
         joinPoint.proceed(); //continue on the intercepted method
         System.out.println("Around after is running!");

         System.out.println("******");

        }*/
}
