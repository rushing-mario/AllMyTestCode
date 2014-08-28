package com.okry.amt.thread.synchicken;

/**
 * 母鸡类
 */
public class Hen {

    /**
     * 下蛋
     *
     * @return
     */
    public Egg lay() {
        Egg egg = new Egg();
        return egg;
    }

    /**
     * 鸡蛋类，需要保证其他类无法生产Egg
     */
    public class Egg {

        private Egg() {
            //私有构造，只有lay方法能产生Egg
        }

        @Override
        public String toString() {
            return "I'm an egg!";
        }

    }

}
