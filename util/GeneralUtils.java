package sgsits.cse.dis.administration.util;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;

/**
 * The type General utils.
 */
@Component
public class GeneralUtils {

  /**
   * Get null property names string [ ].
   *
   * @param source the source
   * @return the string [ ]
   */
  private static String[] getNullPropertyNames(Object source) {
    final BeanWrapper src = new BeanWrapperImpl(source);
    final java.beans.PropertyDescriptor[] propertyDescriptors =
        src.getPropertyDescriptors();
    final Set<String> emptyNames = new HashSet<>();
    for (final java.beans.PropertyDescriptor propertyDescriptor :
        propertyDescriptors) {
      final Object srcValue =
          src.getPropertyValue(propertyDescriptor.getName());
      if (Objects.isNull(srcValue)) {
        emptyNames.add(propertyDescriptor.getName());
      }
    }
    final String[] result = new String[emptyNames.size()];
    return emptyNames.toArray(result);
  }

  /**
   * Copy not null properties.
   *
   * @param source      the source
   * @param destination the destination
   */
  public void copyNotNullProperties(Object source, Object destination) {
    BeanUtils.copyProperties(source, destination,
        getNullPropertyNames(source));
  }
}
