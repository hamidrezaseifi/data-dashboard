package de.mediqon.generic.data_dashboard_common.credential;

public interface ISecureHashProvider {

    String hashMessage(final String message,
                       final String salt,
                       final String pepper,
                       final int iterations,
                       final int length);

    byte[] hashMessage(final char[] message,
                       final byte[] salt,
                       final byte[] pepper,
                       final int iterations,
                       final int length);
}