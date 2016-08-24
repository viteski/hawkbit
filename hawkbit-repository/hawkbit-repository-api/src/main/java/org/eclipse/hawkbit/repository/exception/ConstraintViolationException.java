/**
 * Copyright (c) 2015 Bosch Software Innovations GmbH and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.hawkbit.repository.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;

import org.eclipse.hawkbit.exception.AbstractServerRtException;
import org.eclipse.hawkbit.exception.SpServerError;

/**
 * the {@link ConstraintViolationException} is thrown when an entity is tried to
 * be saved which has constraint violations
 */
public class ConstraintViolationException extends AbstractServerRtException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor for {@link ConstraintViolationException}
     * 
     * @param ex
     *            the javax.validation.ConstraintViolationException which is
     *            thrown
     */
    public ConstraintViolationException(final javax.validation.ConstraintViolationException ex) {
        super(setExceptionMessage(ex), SpServerError.SP_REPO_CONSTRAINT_VIOLATION);
    }

    public static String setExceptionMessage(final javax.validation.ConstraintViolationException ex) {
        final Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        final List<String> messages = new ArrayList<>();
        violations.stream()
                .forEach(violation -> messages.add(violation.getPropertyPath() + " " + violation.getMessage() + "."));

        return messages.stream().collect(Collectors.joining(" "));
    }

}
