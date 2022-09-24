package tup.inde;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IndeApplication {
	@Autowired
	private SortNumbers sortNumbers;

	public static void main(String[] args) {
		SpringApplication.run(IndeApplication.class, args);
		new IndeApplication();
	}

	public IndeApplication() {
		sort(new int[] { 31, 22, 13, 43, 15, 6, 37 });
	}

	private void sort(int[] data) {
		this.sortNumbers.sortNumbers(data);
	}
}
