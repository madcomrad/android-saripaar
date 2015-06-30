/*
 * Copyright (C) 2014 Mobs & Geeks
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mobsandgeeks.saripaar;

import android.content.Context;

/**
 * This is a base interface for {@link com.mobsandgeeks.saripaar.AnnotationRule} and
 * {@link com.mobsandgeeks.saripaar.QuickRule}.
 *
 * @param <VALIDATABLE>  A data type for an {@link com.mobsandgeeks.saripaar.AnnotationRule} and
 *      a {@link android.view.View} for a {@link com.mobsandgeeks.saripaar.QuickRule}.
 *
 * @author Ragunath Jawahar {@literal <rj@mobsandgeeks.com>}
 * @since 1.0
 */
public abstract class Rule<VALIDATABLE> {

    public static final int FLAG_ULTIMATE = 1;
    public static final int FLAG_FOCUS_LOST = 3;

    protected final int mSequence;
    protected final int mFlags;

    /**
     * Constructor.
     *
     * @param sequence  The sequence number for this {@link com.mobsandgeeks.saripaar.Rule}.
     */
    protected Rule(final int sequence) {
        this(sequence, 0);
    }


    protected Rule(final int sequence, final int flags) {
        mSequence = sequence;
        mFlags = flags;
    }
    /**
     * Checks if the rule is valid.
     *
     * @param validatable  Element on which the validation is applied, could be a data type or a
     *      {@link android.view.View}.
     *
     * @return true if valid, false otherwise.
     */
    public abstract boolean isValid(VALIDATABLE validatable);

    /**
     * Returns a failure message associated with the rule.
     *
     * @param context  Any {@link android.content.Context} instance, usually an
     *      {@link android.app.Activity}.
     *
     * @return A failure message.
     */
    public abstract String getMessage(Context context);

    /**
     * Returns the sequence of the {@link com.mobsandgeeks.saripaar.Rule}.
     *
     * @return The sequence.
     */
    public final int getSequence() {
        return mSequence;
    }

    /**
     * Returns the ultimateness of the {@link com.mobsandgeeks.saripaar.Rule}.
     *
     * @return The ultimateness.
     */
    public final boolean isUltimate() {
        return (mFlags & FLAG_ULTIMATE) != 0;
    }

    public final boolean isValidateOnFocusLost() {
        return (mFlags & FLAG_FOCUS_LOST) != 0;
    }

    public abstract int getErrorCode();

    public void setMessage(String message) {
        
    }
}
