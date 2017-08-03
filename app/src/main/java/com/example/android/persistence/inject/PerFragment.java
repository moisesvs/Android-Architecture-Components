/*
 *  Copyright (c) 2016 BBVA. All Rights Reserved.
 *
 *  This software is the confidential and proprietary information of
 *  BBVA ("Confidential Information").  You shall not disclose such
 *  Confidential Information and shall use it only in accordance with
 *  the terms of the license agreement you entered into with BBVA.
 */

package com.example.android.persistence.inject;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * A scoping annotation to permit objects whose lifetime should conform to the life of the activity to be memorized in
 * the correct component.
 * @author Moises
 */
@Scope
@Retention(RUNTIME)
public @interface PerFragment {
}