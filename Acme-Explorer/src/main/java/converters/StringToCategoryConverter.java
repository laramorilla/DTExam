
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import repositories.CategoryRepository;
import domain.Category;

public class StringToCategoryConverter implements Converter<String, Category> {

	@Autowired
	CategoryRepository	categoryRepository;


	@Override
	public Category convert(final String text) {
		Category result = null;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.categoryRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException();
		}

		return result;
	}
}
