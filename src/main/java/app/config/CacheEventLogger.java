package app.config;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.event.CacheEventListener;
import net.sf.ehcache.event.CacheEventListenerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * Слушатель событий, вызванных при работе с кэшем
 */
public class CacheEventLogger extends CacheEventListenerFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(CacheEventLogger.class);

    @Override
    public CacheEventListener createCacheEventListener(Properties properties) {
        return new CacheEventListener() {

            @Override
            public Object clone() throws CloneNotSupportedException {
                LOGGER.info("Клонирование выполнено");
                return super.clone();
            }

            @Override
            public void notifyElementRemoved(Ehcache cache, Element element) throws CacheException {
                LOGGER.info("Элемент удален из кэша : " + element.getObjectValue());
            }

            @Override
            public void notifyElementPut(Ehcache cache, Element element) throws CacheException {
                LOGGER.info("Добавлен в кэш : " + element.getObjectValue());
            }

            @Override
            public void notifyElementUpdated(Ehcache cache, Element element) throws CacheException {
                LOGGER.info("Элемент в кэше обновлен : " + element.getObjectValue());
            }

            @Override
            public void notifyElementExpired(Ehcache cache, Element element) {
                LOGGER.info("Время нахождения следующего элемента в кэшэ истекло : " + element.getObjectValue());
            }

            @Override
            public void notifyElementEvicted(Ehcache cache, Element element) {
                LOGGER.info("Элемент удален из кэша : " + element.getObjectValue());
            }

            @Override
            public void notifyRemoveAll(Ehcache cache) {
                LOGGER.info("Из кэша удалены все элементы");
            }

            @Override
            public void dispose() {
                LOGGER.info("Слушатель размещен");

            }
        };
    }
}