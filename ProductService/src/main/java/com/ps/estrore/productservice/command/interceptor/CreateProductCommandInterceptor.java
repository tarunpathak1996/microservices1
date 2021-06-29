package com.ps.estrore.productservice.command.interceptor;

import java.util.List;
import java.util.function.BiFunction;

import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ps.estrore.productservice.command.CreateProductCommand;
import com.ps.estrore.productservice.core.data.ProductLookupEntity;
import com.ps.estrore.productservice.core.data.ProductLookupRepository;

@Component
public class CreateProductCommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {

	private static final Logger LOGGER = LoggerFactory.getLogger(CreateProductCommandInterceptor.class);

	private final ProductLookupRepository productLookupRepository;

	@Autowired
	public CreateProductCommandInterceptor(ProductLookupRepository productLookupRepository) {
		this.productLookupRepository = productLookupRepository;
	}

	@Override
	public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(
			List<? extends CommandMessage<?>> messages) {

		return (index, command) -> {

			LOGGER.info("Intercept command: " + command.getPayloadType());

			if (CreateProductCommand.class.equals(command.getPayloadType())) {

				CreateProductCommand createProductCommand = (CreateProductCommand) command.getPayload();
				ProductLookupEntity entity = productLookupRepository
						.findByProductIdOrTitle(createProductCommand.getProductId(), createProductCommand.getTitle());
				if (entity != null) {
					throw new IllegalStateException(String.format("Product with prodictId %s or title %s alrady exist",
							createProductCommand.getProductId(), createProductCommand.getTitle()));
				}
			}
			return command;
		};

	}

}
